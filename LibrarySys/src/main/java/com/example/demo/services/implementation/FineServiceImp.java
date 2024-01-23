package com.example.demo.services.implementation;

import com.example.demo.controlers.resources.FineRes;
import com.example.demo.entities.Fine;
import com.example.demo.repository.FineRepository;
import com.example.demo.services.FineService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.mapper.FineMapper.FINE_MAPPER;

@Service
@RequiredArgsConstructor
public class FineServiceImp implements FineService {
    private final FineRepository fineRepository;
    private final UserService userServise;
    @Override
    public List<FineRes> findAll() {
        return FINE_MAPPER.toFineResList(fineRepository.findAll());
    }
    

    @Override
    public FineRes findById(Long id) {
        return FINE_MAPPER.fineToFineRes(fineRepository.findById(id).orElse(null));
    }

    @Override
    public FineRes save(FineRes resoures) {
        Fine fineTemp = FINE_MAPPER.fineToResuorce(resoures);
        fineTemp.setUser(null);

        return FINE_MAPPER.fineToFineRes(fineRepository.save(fineTemp));
    }

    @Override
    public FineRes update(FineRes resoures, Long id) {
        Fine fineTemp = fineRepository.getReferenceById(id);
        userServise.update(resoures.getUser(), resoures.getUser().getId());
        fineTemp.setAmount(resoures.getAmount());
        fineTemp.setDueDate(resoures.getDueDate());
        fineTemp.setPaid(resoures.getIsPaid());



        return FINE_MAPPER.fineToFineRes(fineRepository.save(fineTemp));
    }

    @Override
    public void deleteById(Long id) {
        fineRepository.deleteById(id);
    }

    @Override
    public void updateAll(List<Fine> fines, List<Fine> fines1) {
        for (Fine fine : fines) {
            if (!fines1.contains(fine)) {
                fineRepository.delete(fine);
            }
        }
        for (Fine fine : fines1) {
            if (!fines.contains(fine)) {
                fineRepository.save(fine);
            }
        }
    }

}
