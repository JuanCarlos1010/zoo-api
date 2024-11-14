package com.openx.zoo.api.services;

import com.openx.zoo.api.exceptions.BadRequestException;
import com.openx.zoo.api.exceptions.InternalServerException;
import com.openx.zoo.api.exceptions.NotFoundException;
import com.openx.zoo.api.entities.Region;
import com.openx.zoo.api.repositories.RegionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> findAllRegions() {
        try {
            return regionRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    public Region getRegionById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Region no enontrada en el id: " + id));
    }

    @Transactional
    public Region createRegion(Region region) {
        Optional<Region> regionOpt = regionRepository.findByName(region.getName());
        if (regionOpt.isPresent()) {
            throw new BadRequestException("Region con el nombre " + region.getName() + " ya existe.");
        }
        return regionRepository.save(region);
    }

    @Transactional
    public Region updateRegion(Region updateRegion) {
        return regionRepository.findById(updateRegion.getId())
                .map(region -> {
                    region.setName(updateRegion.getName());
                    return regionRepository.save(region);
                })
                .orElseThrow(() -> new NotFoundException("Region no encontrada con el id: " + updateRegion.getId()));
    }

    @Transactional
    public boolean deleteRegion(Long id) {
        Region region = getRegionById(id);
        regionRepository.delete(region);
        return true;
    }
}
