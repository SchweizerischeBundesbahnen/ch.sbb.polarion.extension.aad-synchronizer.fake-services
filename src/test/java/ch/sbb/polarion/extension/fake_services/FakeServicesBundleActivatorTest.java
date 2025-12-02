package ch.sbb.polarion.extension.fake_services;

import ch.sbb.polarion.extension.aad.synchronizer.connector.IGraphConnector;
import ch.sbb.polarion.extension.aad.synchronizer.service.IPolarionServiceFactory;
import ch.sbb.polarion.extension.fake_services.connector.FakeGraphConnector;
import ch.sbb.polarion.extension.fake_services.service.FakePolarionServiceFactory;
import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SuppressWarnings({"rawtypes", "unchecked"})
class FakeServicesBundleActivatorTest {

    private FakeServicesBundleActivator activator;
    private BundleContext context;

    @BeforeEach
    void setUp() {
        activator = new FakeServicesBundleActivator();
        context = mock(BundleContext.class);
    }

    @Test
    @SneakyThrows()
    void testStartRegistersServices() {
        ServiceRegistration mockGraphReg = mock(ServiceRegistration.class);
        ServiceRegistration mockPolarionReg = mock(ServiceRegistration.class);

        when(context.registerService(eq(IGraphConnector.class), any(FakeGraphConnector.class), any()))
                .thenReturn(mockGraphReg);
        when(context.registerService(eq(IPolarionServiceFactory.class), any(FakePolarionServiceFactory.class), any()))
                .thenReturn(mockPolarionReg);

        activator.start(context);

        verify(context).registerService(eq(IGraphConnector.class), any(FakeGraphConnector.class), any());
        verify(context).registerService(eq(IPolarionServiceFactory.class), any(FakePolarionServiceFactory.class), any());
    }

    @Test
    @SneakyThrows()
    void testStartRegistersServicesSkippedByException() {
        try (MockedStatic<ConstructorUtils> constructorUtils = mockStatic(ConstructorUtils.class)) {
            constructorUtils.when(() -> ConstructorUtils.invokeConstructor(any(), any()))
                    .thenThrow(new RuntimeException("Simulated constructor exception"));

            activator.start(context);

            verify(context, never()).registerService(any(Class.class), any(Object.class), any(Hashtable.class));
        }
    }

    @Test
    @SneakyThrows
    void testStopUnregistersServices() {
        ServiceRegistration mockGraphReg = mock(ServiceRegistration.class);
        ServiceRegistration mockPolarionReg = mock(ServiceRegistration.class);

        when(context.registerService(eq(IGraphConnector.class), any(FakeGraphConnector.class), any()))
                .thenReturn(mockGraphReg);
        when(context.registerService(eq(IPolarionServiceFactory.class), any(FakePolarionServiceFactory.class), any()))
                .thenReturn(mockPolarionReg);

        activator.start(context);
        activator.stop(context);

        verify(mockGraphReg).unregister();
        verify(mockPolarionReg).unregister();
    }
}
