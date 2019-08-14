package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    Long ownerId = 1L;
    String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void save() {
        Owner owner2 = Owner.builder().id(2L).build();
        Owner ownerSaved = ownerServiceMap.save(owner2);
        assertEquals(owner2.getId(),ownerSaved.getId());
    }

    @Test
    void saveNoId(){
        Owner ownerSaved = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(ownerSaved);
        assertNotNull(ownerSaved.getId());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void delete() {
    ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
    assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {

        Owner smith = ownerServiceMap.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(smith.getId(),ownerId);
    }

    @Test
    void findByLastNameNotFound(){
        Owner foo = ownerServiceMap.findByLastName("foo");

        assertNull(foo);
    }
}