package ch.sbb.polarion.extension.fake_services.service;

import ch.sbb.polarion.extension.generic.util.PObjectListStub;
import com.polarion.alm.projects.IProjectService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FakePolarionServiceFactoryTest {

    @Test
    @SuppressWarnings("unchcked")
    void testInitialization() {
        FakePolarionServiceFactory factory = new FakePolarionServiceFactory();
        IProjectService projectService = mock(IProjectService.class);
        when(projectService.getUsers()).thenReturn(new PObjectListStub<>(List.of()));
        List<String> memberIds = List.of();
        assertDoesNotThrow(() -> factory.createPolarionService(null, projectService, false, memberIds));
    }

}
