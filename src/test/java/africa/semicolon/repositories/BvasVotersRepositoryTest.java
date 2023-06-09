package africa.semicolon.repositories;

import africa.semicolon.models.Address;
import africa.semicolon.models.Voter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
class BvasVotersRepositoryTest {

    private final BvasVotersRepository repository = new BvasVotersRepository();
    private Voter voter;
    private Address address;
    private Voter savedVoter;
    @BeforeEach
    void setUp(){
        voter = createVoter();
        savedVoter = repository.save(voter);
    }

    private Voter createVoter() {
        Voter voter = Voter.builder().age(23).address(createAddress()).build();
        return voter;
    }

    private Address createAddress() {
        address = Address.builder().street("evil street").build();
        return address;
    }

    @Test
    void testSaveMethod(){
        Voter voter1 = repository.save(voter);
        assertNotNull(voter);
        assertEquals(voter.getId(), voter1.getId());
        assertEquals("evil street", voter1.getAddress().getStreet());
    }

    @Test
    void testFindById(){
        Voter voter1 = repository.save(voter);
        assertEquals(voter , repository.findById(voter1.getId()));
    }
    @Test
    void testSaveMultipleVoter(){
        Voter secondVoter = repository.save(createVoter());
        Voter thirdVoter = repository.save(createVoter());
        assertNotNull(thirdVoter);
        assertNotNull(secondVoter);
        assertEquals(3, repository.findAll().size());
    }
    @Test
    void deleteVoterById(){
        Voter secondVoter = repository.save(createVoter());
        assertEquals(BigInteger.valueOf(2).intValue(), repository.findAll().size());
        repository.deleteById(savedVoter.getId());
        assertEquals(1, repository.findAll().size());


    }
}