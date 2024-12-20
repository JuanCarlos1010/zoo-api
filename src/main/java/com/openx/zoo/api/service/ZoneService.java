package com.openx.zoo.api.service;

import com.openx.zoo.api.exception.BadRequestException;
import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.entity.Region;
import com.openx.zoo.api.entity.Zone;
import com.openx.zoo.api.repository.RegionRepository;
import com.openx.zoo.api.repository.ZoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<Zone> findAllZones() {
        try {
            return zoneRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Zona no encontrada en el id: " + id));
    }

    @Transactional
    public Zone createZone(Zone zone) {
        Region region = zone.getRegion();
        if (region == null) {
            throw new BadRequestException("El campo region es obligatorio");
        } else {
            Optional<Region> regionOpt = regionRepository.findById(region.getId());
            if (regionOpt.isEmpty()) {
                throw new NotFoundException("Region no encontrada con id: " + region.getId());
            }
            Optional<Zone> zonaOpt = zoneRepository.findByName(zone.getName());
            if (zonaOpt.isPresent()) {
                throw new BadRequestException("Zona con el nombre " + zone.getName() + " ya existe");
            }
            zone.setRegion(regionOpt.get());
            zone.setCreatedAt(LocalDateTime.now());
            return zoneRepository.save(zone);
        }
    }

    @Transactional
    public Zone updateZone(Zone updateZone) {
        Region region = updateZone.getRegion();
        if (region == null) {
            throw new BadRequestException("El campo region es obligatorio");
        }
        return zoneRepository.findById(updateZone.getId())
                .map(zone -> {
                    Optional<Region> regionOpt = regionRepository.findById(region.getId());
                    if (regionOpt.isEmpty()) {
                        throw new NotFoundException("Region no encontrada con id: " + region.getId());
                    }
                    zone.setName(updateZone.getName());
                    zone.setCapacity(updateZone.getCapacity());
                    zone.setType(updateZone.getType());
                    zone.setUpdatedAt(updateZone.getUpdatedAt());
                    zone.setRegion(regionOpt.get());
                    return zoneRepository.save(zone);
                })
                .orElseThrow(() -> new NotFoundException("Zona no encontrada con el id: " + updateZone.getId()));
    }

    @Transactional
    public boolean deleteZone(Long id) {
        Zone zone = getZoneById(id);
        zoneRepository.delete(zone);
        return true;
    }
}
