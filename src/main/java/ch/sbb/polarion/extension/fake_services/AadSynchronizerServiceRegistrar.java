package ch.sbb.polarion.extension.fake_services;

import ch.sbb.polarion.extension.aad.synchronizer.connector.IGraphConnector;
import ch.sbb.polarion.extension.aad.synchronizer.service.IPolarionServiceFactory;
import ch.sbb.polarion.extension.fake_services.connector.FakeGraphConnector;
import ch.sbb.polarion.extension.fake_services.service.FakePolarionServiceFactory;
import com.polarion.core.util.logging.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@SuppressWarnings({"rawtypes", "unused"})
public class AadSynchronizerServiceRegistrar {

    private static final Logger logger = Logger.getLogger(AadSynchronizerServiceRegistrar.class);

    public List<ServiceRegistration> register(BundleContext context) {
        logger.info("Registering IGraph services.");

        List<ServiceRegistration> registrations = new ArrayList<>();

        registrations.add(context.registerService(
                IGraphConnector.class,
                new FakeGraphConnector(),
                new Hashtable<String, String>()));

        registrations.add(context.registerService(
                IPolarionServiceFactory.class,
                new FakePolarionServiceFactory(),
                new Hashtable<String, String>()));

        logger.info("IGraph services have been registered.");

        return registrations;
    }

}
