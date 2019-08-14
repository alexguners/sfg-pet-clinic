package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    String smith1 = "Smith";

    @InjectMocks
    OwnerSDJpaService service;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    Owner ownerSaved;

    @BeforeEach
    void setUp() {
        ownerSaved= Owner.builder().id(3L).build();
    }

    @Test
    void findByLastName() {
        Owner returnOwner = Owner.builder().id(1L).lastName(smith1).build();
        when(service.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(smith1);

        assertEquals(smith1,smith.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        when(service.findByLastName(any())).thenReturn(null);

        Owner smith = service.findByLastName(smith1);

        assertNull(smith);
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(service.findAll()).thenReturn(owners);

        Set<Owner> resultOwners = service.findAll();

        assertEquals(2,resultOwners.size());
        assertNotNull(resultOwners);
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(ownerSaved));

        Owner owner = service.findById(3L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotfound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(3L);

        assertNull(owner);
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(owner);

        Owner savedOwner = service.save(owner);

        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        service.delete(ownerSaved);

        verify(ownerRepository).delete(any());

    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }
}