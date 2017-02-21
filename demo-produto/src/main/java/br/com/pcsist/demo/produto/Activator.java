package br.com.pcsist.demo.produto;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator {

  private final Logger LOG = LoggerFactory.getLogger(Activator.class);

  @Override
  public void start(BundleContext context) throws Exception {
    LOG.info("Serviços de WinThor/Produto iniciado");
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    LOG.info("Serviços de WinThor/Produto paralizado");
  }

}
