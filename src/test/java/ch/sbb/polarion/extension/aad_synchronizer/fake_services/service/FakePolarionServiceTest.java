package ch.sbb.polarion.extension.aad_synchronizer.fake_services.service;

import com.polarion.alm.projects.IProjectService;
import com.polarion.alm.projects.model.IUser;
import com.polarion.platform.persistence.model.IPObjectList;
import com.polarion.platform.security.ISecurityService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FakePolarionServiceTest {
    @Test
    @SneakyThrows
    void testFakePolarionService_withMocksAndReflection() {
        ISecurityService mockSecurityService = mock(ISecurityService.class);
        IProjectService mockProjectService = mock(IProjectService.class);

        IPObjectList mockUserList = mock(IPObjectList.class);
        when(mockProjectService.getUsers()).thenReturn(mockUserList);

        IUser mockUser1 = mock(IUser.class);
        IUser mockUser2 = mock(IUser.class);
        when(mockUserList.stream()).thenReturn(Stream.of(mockUser1, mockUser2));

        List<String> memberIds = Arrays.asList("user1", "dev2");
        Random controlledRandom = new Random() {
            private int count = 0;

            @Override
            public boolean nextBoolean() {
                return count++ % 2 == 0;
            }
        };

        FakePolarionService service = new FakePolarionService(
                mockSecurityService, mockProjectService, false, memberIds, controlledRandom
        );

        Method method = FakePolarionService.class.getDeclaredMethod("getAllPolarionUsers");
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        List<String> result = (List<String>) method.invoke(service);

        assertTrue(result.contains("USER1"));
        assertFalse(result.contains("dev2"));
        assertTrue(result.contains("testuser"));
        assertTrue(result.contains("testUser"));
        assertTrue(result.contains("TESTUSER"));
    }
}
