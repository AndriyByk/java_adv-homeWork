package ua.project.java_advhomework.controllers;

import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.models.HomePet;
import ua.project.java_advhomework.models.Owner;
import ua.project.java_advhomework.models.Type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CRUDController {
    private List<HomePet> pets;
    private List<Owner> owners;

    public CRUDController() {
        pets = new ArrayList<>();
        owners = new ArrayList<>();

        owners.add(new Owner(1, "Masha", true));
        owners.add(new Owner(2, "Taras", true));
        owners.add(new Owner(3, "Petro", true));
        owners.add(new Owner(4, "Yulia", false));


        pets.add(new HomePet(1, "Sirko", 4, owners.get(0), Type.CAT));
        pets.add(new HomePet(2, "Kokos", 3, owners.get(2), Type.DOG));
        pets.add(new HomePet(3, "Ananas", 5, owners.get(0), Type.DOG));
        pets.add(new HomePet(4, "Boni", 3, owners.get(1), Type.DOG));
        pets.add(new HomePet(5, "Samson", 1, owners.get(2), Type.CAT));
        pets.add(new HomePet(6, "Ork", 10, owners.get(2), Type.CAT));
        pets.add(new HomePet(7, "Tomat", 12, owners.get(1), Type.DOG));
        pets.add(new HomePet(8, "Wolf", 6, owners.get(3), Type.CAT));
    }

//============================================================================
    @GetMapping("/home")
    public String home() {
        return "Hello everybody! Here you will find your new four-legged friend.";
    }

//============================================================================
    @GetMapping("/pets")
    public List<HomePet> homePets() {
        return pets;
    }

    @GetMapping("/owners")
    public List<Owner> owners() {
        return owners;
    }

//============================================================================
    @GetMapping ("/pets/{id}")
    public HomePet homePet(@PathVariable("id") int id) {
        return pets.stream().filter(pet -> pet.getId() == id).findFirst().get();
    }

    @GetMapping ("/owners/{id}")
    public Owner owner(@PathVariable("id") int id) {
        return owners.stream().filter(owner -> owner.getId() == id).findFirst().get();
    }

//----------- with params -----------
    @GetMapping("/pet")
    public HomePet homePetByParam(@RequestParam("petId") int petId) {
        return pets.stream().filter(homePet -> homePet.getId() == petId).findFirst().get();
    }

    @GetMapping ("/owner")
    public Owner ownerByParam(@RequestParam("ownerId") int ownerId) {
        return owners.stream().filter(owner -> owner.getId() == ownerId).findFirst().get();
    }

//============================================================================
    @PostMapping("/pets")
    public List<HomePet> saveNewPet(@RequestBody HomePet homePet) {
        pets.add(homePet);
        owners.add(homePet.getOwner());
        return pets;
    }

    @PostMapping("/owners")
    public List<Owner> saveNewOwner(@RequestBody Owner owner) {
        owners.add(owner);
        return owners;
    }

//============================================================================
    @PutMapping("/pets/{id}")
    public List<HomePet> changePet(@PathVariable int id, @RequestBody HomePet homePet) {
        HomePet changeablePet = pets.stream().filter(pet -> pet.getId() == id).findFirst().get();
        int certainId = pets.indexOf(changeablePet);
        pets.set(certainId, homePet);
        return pets;
    }

    @PutMapping("/owners/{id}")
    public List<Owner> changeOwner(@PathVariable int id, @RequestBody Owner owner) {
        Owner changeableOwner = owners.stream().filter(owner1 -> owner1.getId() == id).findFirst().get();
        int certainId = owners.indexOf(changeableOwner);
        owners.set(certainId, owner);
        return owners;
    }

//============================================================================
    @DeleteMapping("/pets/{id}")
    public List<HomePet> deleteHomePet(@PathVariable int id) {
        Iterator<HomePet> iterator = pets.iterator();
        while (iterator.hasNext()) {
            HomePet pet = iterator.next();
            if (pet.getId() == id) {
                iterator.remove();
            }
        }
        return pets;
    }

    @DeleteMapping("/owners/{id}")
    public List<Owner> deleteOwner(@PathVariable int id) {
        Iterator<Owner> iterator = owners.iterator();
        while (iterator.hasNext()) {
            Owner owner = iterator.next();
            if (owner.getId() == id) {
                iterator.remove();
            }
        }
        return owners;
    }

//----------- with params -----------
    @DeleteMapping("/pet")
    public List<HomePet> deleteHomePetByParam(@RequestParam("petId") int petId) {
        Iterator<HomePet> iterator = pets.iterator();
        while (iterator.hasNext()) {
            HomePet pet = iterator.next();
            if (pet.getId() == petId) {
                iterator.remove();
            }
        }
        return pets;
    }

    @DeleteMapping("/owner")
    public List<Owner> deleteOwnerByParam(@RequestParam("ownerId") int ownerId) {
        Iterator<Owner> iterator = owners.iterator();
        while (iterator.hasNext()) {
            Owner owner = iterator.next();
            if (owner.getId() == ownerId) {
                iterator.remove();
            }
        }
        return owners;
    }
}
