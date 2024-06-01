package capstone.zigtong.adminserver.domain.certificate.dto;

import capstone.zigtong.adminserver.domain.certificate.Certificate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CertificateDto {
    Integer id;
    String affiliation;
    String item;

    public CertificateDto(Certificate certificate) {
        id = certificate.getId();
        affiliation = certificate.getAffiliation();
        item = certificate.getItem();
    }
}
