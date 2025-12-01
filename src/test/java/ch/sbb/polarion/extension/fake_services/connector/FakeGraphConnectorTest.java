package ch.sbb.polarion.extension.fake_services.connector;

import ch.sbb.polarion.extension.aad.synchronizer.model.Group;
import ch.sbb.polarion.extension.aad.synchronizer.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FakeGraphConnectorTest {
    private FakeGraphConnector connector;

    @BeforeEach
    void setUp() {
        connector = new FakeGraphConnector();
    }

    @Test
    void testGetGroups() {
        String groupPrefix = "TestGroup_";
        List<Group> groups = connector.getGroups(groupPrefix);

        assertTrue(!groups.isEmpty() && groups.size() <= 5);

        for (Group group : groups) {
            assertTrue(group.getId().startsWith(groupPrefix));
            assertTrue(group.getId().contains("-"));
        }
    }

    @Test
    void testGetMembers() {
        String key = "TestKey";
        List<Member> members = connector.getMembers(key);

        assertTrue(!members.isEmpty() && members.size() <= 15);

        for (Member member : members) {
            assertNotNull(member.getName());
            assertNotNull(member.getEmail());
        }
    }
}
