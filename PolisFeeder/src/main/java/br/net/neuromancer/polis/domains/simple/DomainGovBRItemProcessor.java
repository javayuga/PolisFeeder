package br.net.neuromancer.polis.domains.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import br.net.neuromancer.polis.domains.model.DomainGovBR;

public class DomainGovBRItemProcessor implements ItemProcessor<DomainGovBR, DomainGovBR> {

    private static final Logger log = LoggerFactory.getLogger(DomainGovBRItemProcessor.class);

    @Override
    public DomainGovBR process(final DomainGovBR domain) throws Exception {
        log.info("Converting (" + domain + ")");
        
        return domain;

    }

}
