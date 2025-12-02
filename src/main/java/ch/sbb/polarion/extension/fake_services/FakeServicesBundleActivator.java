package ch.sbb.polarion.extension.fake_services;

import com.polarion.core.util.logging.Logger;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class FakeServicesBundleActivator implements BundleActivator {
    private static final Logger logger = Logger.getLogger(FakeServicesBundleActivator.class);

    private final List<ServiceRegistration> serviceRegistrations = new ArrayList<>();

    @Override
    public void start(BundleContext context) {
        try {
            Class<?> registrarClass = Class.forName("ch.sbb.polarion.extension.fake_services.AadSynchronizerServiceRegistrar");
            Object registrar = ConstructorUtils.invokeConstructor(registrarClass);
            serviceRegistrations.addAll((List<ServiceRegistration>) MethodUtils.invokeMethod(registrar, "register", context));
        } catch (Exception e) {
            logger.warn("AAD Synchronizer classes not found. This is expected if AAD-Synchronizer is not installed; skipping fake IGraph services registration.");
        }
    }

    @Override
    public void stop(BundleContext context) {
        logger.info("Unregistering services.");
        serviceRegistrations.forEach(ServiceRegistration::unregister);
        logger.info("Services have been unregistered.");
    }
}
