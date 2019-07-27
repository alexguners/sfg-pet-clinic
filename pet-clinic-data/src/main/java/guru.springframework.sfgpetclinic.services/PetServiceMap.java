package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.map.AbstractMapService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet,Long> implements CrudService<Pet,Long> {

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(),object);
    }

    @Override
    public Set<Pet> findAll(){
        return super.findAll();
    }

    @Override
    public Pet findById(Long id){
        return super.findById(id);
    }

    @Override
    public void delete(Pet object){
        super.delete(object);
    }

    @Override
    public void deleteById(Long id){
        super.deleteById(id);
    }
}
