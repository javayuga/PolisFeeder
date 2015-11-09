package br.net.neuromancer.polis.domains;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import br.net.neuromancer.polis.domains.model.DomainGovBR;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	// tag::readerwriterprocessor[]
	@Bean
	public ItemReader<DomainGovBR> reader() {
		FlatFileItemReader<DomainGovBR> reader = new FlatFileItemReader<DomainGovBR>();
		reader.setLinesToSkip(1);
		reader.setStrict(false);
		reader.setResource(new ClassPathResource("data/Dominios_GovBR_basico.csv"));
		reader.setLineMapper(new DefaultLineMapper<DomainGovBR>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "domain", "document", "name", "uf", "city", "cep", "inclusionDate",
								"lastUpdate", "ticketNo", "filler" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<DomainGovBR>() {
					{
						setTargetType(DomainGovBR.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public ItemProcessor<DomainGovBR, DomainGovBR> processor() {
		return new DomainGovBRItemProcessor();
	}

	@Bean
	public ItemWriter<DomainGovBR> writer(DataSource dataSource) {
		JdbcBatchItemWriter<DomainGovBR> writer = new JdbcBatchItemWriter<DomainGovBR>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<DomainGovBR>());
		writer.setSql(
				"INSERT INTO domains (domain, document, name, uf, city, cep, inclusionDate, lastUpdate, ticketNo) VALUES (:domain, :document, :name, :uf, :city, :cep, :inclusionDate, :lastUpdate, :ticketNo)");
		writer.setDataSource(dataSource);
		return writer;
	}
	// end::readerwriterprocessor[]

	// tag::jobstep[]
	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
		return jobs.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(s1).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<DomainGovBR> reader,
			ItemWriter<DomainGovBR> writer, ItemProcessor<DomainGovBR, DomainGovBR> processor) {
		return stepBuilderFactory.get("step1").<DomainGovBR, DomainGovBR> chunk(10).reader(reader).processor(processor)
				.writer(writer).build();
	}
	// end::jobstep[]

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
