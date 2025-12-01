package ch.sbb.polarion.extension.fake_services.service;

import com.polarion.alm.projects.IProjectService;
import com.polarion.alm.projects.model.IUser;
import com.polarion.platform.persistence.model.IPObjectList;
import com.polarion.platform.security.ISecurityService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings({"rawtypes", "unchecked"})
class FakePolarionServiceTest {
    @Test
    @SneakyThrows
    void testFakePolarionService_withMocksAndReflection() {
        ISecurityService mockSecurityService = mock(ISecurityService.class);
        IProjectService mockProjectService = mock(IProjectService.class);

        IPObjectList mockUserList = mock(IPObjectList.class);
        when(mockProjectService.getUsers()).thenReturn(mockUserList);

        IUser mockUser1 = mock(IUser.class);
        when(mockUser1.getId()).thenReturn("mockUser1");
        IUser mockUser2 = mock(IUser.class);
        when(mockUser2.getId()).thenReturn("mockUser2");
        when(mockUserList.stream()).thenAnswer(invocationOnMock -> Stream.of(mockUser1, mockUser2));

        List<String> memberIds = List.of("user1", "dev2");
        SecureRandom controlledRandom = mock(SecureRandom.class);
        when(controlledRandom.nextBoolean()).thenReturn(true);
        FakePolarionService service = new FakePolarionService(mockSecurityService, mockProjectService, false, memberIds, controlledRandom);
        assertTrue(service.getAllPolarionUsers().containsAll(List.of("USER1", "dev2", "testuser", "testUser", "TESTUSER")));

        when(controlledRandom.nextBoolean()).thenReturn(false);
        service = new FakePolarionService(mockSecurityService, mockProjectService, false, memberIds, controlledRandom);
        assertTrue(service.getAllPolarionUsers().stream().noneMatch(v -> List.of("USER1", "dev2", "testuser", "testUser", "TESTUSER").contains(v)));
    }
}
