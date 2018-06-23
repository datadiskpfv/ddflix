package uk.co.datadisk.ddflix.services.disc.impl;

import org.springframework.stereotype.Service;
import uk.co.datadisk.ddflix.dto.mapper.disc.DiscFormMapper;
import uk.co.datadisk.ddflix.dto.models.disc.DiscFormDTO;
import uk.co.datadisk.ddflix.entities.disc.Disc;
import uk.co.datadisk.ddflix.repositories.disc.DiscRepository;
import uk.co.datadisk.ddflix.services.disc.DiscService;

import java.util.List;

@Service
public class DiscServiceImpl implements DiscService {

    private final DiscRepository discRepository;
    private final DiscFormMapper discFormMapper;

    public DiscServiceImpl(DiscRepository discRepository, DiscFormMapper discFormMapper) {
        this.discRepository = discRepository;
        this.discFormMapper = discFormMapper;
    }

    @Override
    public Disc saveDisc(Disc disc) {
        return discRepository.save(disc);
    }

    @Override
    public List<Disc> findAll() {
        return discRepository.findAll();
    }

    @Override
    public void deleteDiscById(Long discId) {
        discRepository.deleteById(discId);
    }

    @Override
    public DiscFormDTO discForm(Long id) {
        Disc disc = discRepository.getOne(id);
        return discFormMapper.DiscToDiscFormDTO(disc);
    }

    @Override
    public DiscFormDTO discFormSave(DiscFormDTO discFormDTO) {
        //Got to get the users password as it is not in the DTO
        Disc disc = discFormMapper.DiscFormDTOToDisc(discFormDTO);
        return (discFormMapper.DiscToDiscFormDTO(this.saveDisc(disc)));
    }


}