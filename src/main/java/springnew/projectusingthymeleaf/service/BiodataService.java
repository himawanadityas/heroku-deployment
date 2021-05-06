package springnew.projectusingthymeleaf.service;

import springnew.projectusingthymeleaf.model.entity.Biodata;

public interface BiodataService {
    Biodata latTransactional();

    Biodata saveBiodataMaterDetail(Biodata biodata);
}
