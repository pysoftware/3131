package ru.sazonov.future1.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sazonov.future1.enteties.Lord;
import ru.sazonov.future1.exceptions.NotFoundEntityException;
import ru.sazonov.future1.mappers.LordMapper;
import ru.sazonov.future1.repositories.LordRepository;
import ru.sazonov.future1.requests.LordModel;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LordService {

    private final LordRepository lordRepository;
    private final LordMapper lordMapper;

    @Transactional
    public void createLord(LordModel lordModel) {
        Lord lord = lordMapper.toLordEntity(lordModel);
        lordRepository.save(lord);
    }

    @Transactional
    public List<Lord> get10YoungestLords() {
        return lordRepository.findFirst10Lords(Pageable.ofSize(10)).getContent();
    }

    @Transactional
    public List<Lord> findAllSlackerLords() {
        return lordRepository.findAllSlackerLords();
    }

    /**
     * @param lordId    lord id
     * @param lordModel lord model
     * @return updated lord
     */
    @Transactional
    public Lord updateLord(Long lordId, LordModel lordModel) {
        Lord targetLord = findLordById(lordId);
        lordMapper.updateLord(lordModel, targetLord);
        // Lord already in context. No need repo.save(targetLord);
        return targetLord;
    }

    @Transactional
    public Lord findLordById(Long lordId) throws NotFoundEntityException {
        return lordRepository.findById(lordId).orElseThrow(() ->
                new NotFoundEntityException(String.format("Lord with id: %d not found", lordId)));
    }
}
