package springnew.projectusingthymeleaf.service;

import springnew.projectusingthymeleaf.model.entity.Agama;
import springnew.projectusingthymeleaf.repository.AgamaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AgamaServiceImpl implements AgamaService {
    @Autowired
    private AgamaRepository agamaRepository;

    public Agama saveAgama(Agama agama) {
        return agamaRepository.save(agama);

    }
}