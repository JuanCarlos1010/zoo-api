package com.openx.zoo.apizoogestion.services;

import com.openx.zoo.apizoogestion.exceptions.BadRequestException;
import com.openx.zoo.apizoogestion.exceptions.InternalServerException;
import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.models.Region;
import com.openx.zoo.apizoogestion.models.Zone;
import com.openx.zoo.apizoogestion.repositories.RegionRepository;
import com.openx.zoo.apizoogestion.repositories.ZoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final RegionRepository regionRepository;

    public ZoneService(ZoneRepository zoneRepository, RegionRepository regionRepository) {
        this.zoneRepository = zoneRepository;
        this.regionRepository = regionRepository;
    }

    public List<Zone> findAllZones(){
        try {
            return zoneRepository.findAll();
        } catch (Exception e){
            throw new InternalServerException(e);
        }
    }

    public Zone getZoneById(Long id){
        return zoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Zona no encontrada en el id: " + id));
    }

    @Transactional
    public Zone createZone(Zone zone){
        Region region = zone.getRegion();
        if (region == null){
            throw new BadRequestException("El campo region es obligatorio");
        } else {
            Optional<Region> regionOpt = regionRepository.findById(region.getId());
            if (regionOpt.isEmpty()){
                throw new NotFoundExeption("Region no encontrada con id: " + region.getId());
            }
            Optional<Zone> zonaOpt = zoneRepository.findByName(zone.getName());
            if (zonaOpt.isPresent()){
                throw new BadRequestException("Zona con el nombre " + zone.getName() + " ya existe.");
            }
            zone.setRegion(regionOpt.get());
            return zoneRepository.save(zone);
        }
    }

    @Transactional
    public Zone updateZone(Zone updateZone){
        Region region = updateZone.getRegion();
        if (region == null){
            throw new BadRequestException("El campo region es obligatorio");
        }
        return zoneRepository.findById(updateZone.getId())
                .map(zone -> {
                    Optional<Region> regionOpt = regionRepository.findById(region.getId());
                    if (regionOpt.isEmpty()){
                        throw new NotFoundExeption("Region no encontrada con id: " + region.getId());
                    }
                    zone.setName(updateZone.getName());
                    zone.setType(updateZone.getType());
                    zone.setCapacity(updateZone.getCapacity());
                    zone.setRegion(regionOpt.get());
                    return zoneRepository.save(zone);
                })
                .orElseThrow(()-> new NotFoundExeption("Zona no encontrada con el id: " + updateZone.getId()));
    }

    @Transactional
    public boolean deleteZone(Long id){
        Zone zone = getZoneById(id);
        zoneRepository.delete(zone);
        return true;
    }
}
