package com.javastaff.spring.scheduler;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServizioSchedulato {
	
	private static final Logger LOG = LoggerFactory.getLogger(ServizioSchedulato.class);
	
	@Scheduled(fixedDelay = 20000)
	public void avvioConIntervalloFissoTraEsecuzioni() {
		LOG.info("Io vengo avviato con un intervallo di 20000 millisecondi rispetto alla precedente esecuzione");
	}
	
	@Scheduled(fixedRate = 15000)
	public void avvioConIntervalloFisso() {
		LOG.info("Io vengo avviato ogni 15000 millisecondi");
	}
	
	@Scheduled(fixedDelay = 30000, initialDelay = 1000)
	public void avvioConIntervalloFissoERitardoIniziale() {
		LOG.info("Io vengo avviato con un intervallo di 30000 millisecondi rispetto alla precedente esecuzione ma con un ritardo iniziale di 1000 millisecondi");
	}
	
	@Scheduled(cron = "0 0 * * * *")
	public void segnaleOrario() {
		int ora=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		LOG.info("Sono le ore {}",ora);
	}
}
