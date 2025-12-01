package ch.sbb.polarion.extension.fake_services;

import ch.sbb.polarion.extension.aad.synchronizer.connector.IGraphConnector;
import ch.sbb.polarion.extension.aad.synchronizer.service.IPolarionServiceFactory;
import ch.sbb.polarion.extension.fake_services.connector.FakeGraphConnector;
import ch.sbb.polarion.extension.fake_services.service.FakePolarionServiceFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
