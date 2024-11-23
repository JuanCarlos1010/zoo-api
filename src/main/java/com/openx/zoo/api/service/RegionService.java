package com.openx.zoo.api.service;

import com.openx.zoo.api.exception.BadRequestException;
import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.entity.Region;
import com.openx.zoo.api.repository.RegionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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

        try {
            Optional<Region> regionOpt = regionRepository.findByName(region.getName());
            if (regionOpt.isPresent()) {
                throw new BadRequestException("Region con el nombre " + region.getName() + " ya existe.");
            }
            region.setCreatedAt(LocalDateTime.now());
            return regionRepository.save(region);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    @Transactional
    public Region updateRegion(Region updateRegion) {
        try {
            return regionRepository.findById(updateRegion.getId())
                    .map(region -> {
                        region.setName(updateRegion.getName());
                        region.setUpdatedAt(LocalDateTime.now());
                        return regionRepository.save(region);
                    })
                    .orElseThrow(() -> new NotFoundException("Region no encontrada con el id: " + updateRegion.getId()));
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    @Transactional
    public boolean deleteRegion(Long id) {
        Region region = getRegionById(id);
        region.setDeletedAt(LocalDateTime.now());
        regionRepository.save(region);
        return true;
    }
}
