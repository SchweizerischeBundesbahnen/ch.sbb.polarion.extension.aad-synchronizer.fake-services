package ch.sbb.polarion.extension.aad_synchronizer_fake_services;

import ch.sbb.polarion.extension.aad.synchronizer.connector.IGraphConnector;
import ch.sbb.polarion.extension.aad.synchronizer.service.IPolarionServiceFactory;
import ch.sbb.polarion.extension.aad_synchronizer_fake_services.connector.FakeGraphConnector;
import ch.sbb.polarion.extension.aad_synchronizer_fake_services.service.FakePolarionServiceFactory;
import com.polarion.core.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

public class FakeServicesBundleActivator implements BundleActivator {
    private static final Logger logger = Logger.getLogger(FakeServicesBundleActivator.class);

    private ServiceRegistration<IGraphConnector> graphConnectorRegistration;
    private ServiceRegistration<IPolarionServiceFactory> polarionServiceFactoryServiceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("Registering IGraph service.");

        graphConnectorRegistration = context.registerService(
                IGraphConnector.class,
                new FakeGraphConnector(),
                new Hashtable<String, String>());

        polarionServiceFactoryServiceRegistration = context.registerService(
                IPolarionServiceFactory.class,
                new FakePolarionServiceFactory(),
                new Hashtable<String, String>());

        logger.info("IGraph service have been registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("Unregistering IGraph service.");

        graphConnectorRegistration.unregister();
        polarionServiceFactoryServiceRegistration.unregister();

        logger.info("IGraph service have been unregistered.");
    }
}