package pal.comp.pgsbackend.mapper;

import org.springframework.stereotype.Component;
import pal.comp.pgsbackend.dto.report.RequestCreateReportDto;
import pal.comp.pgsbackend.dto.report.ResponseReportDto;
import pal.comp.pgsbackend.entity.ReportEntity;

@Component
public class ReportMapper {


    public ResponseReportDto toDto(ReportEntity entity) {
        return new ResponseReportDto(
                entity.getId(),
                entity.getPlan(),
                entity.getFact(),
                entity.getDelta(),
                entity.getDate(),
                entity.getWhoSend(),
                entity.getMachine(),
                entity.getComment()
        );
    }

    public ReportEntity toEntity(RequestCreateReportDto dto) {
        return new ReportEntity(
                null,
                dto.planId(),
                dto.fact(),
                0F,
                dto.date(),
                dto.whoSend(),
                dto.machine(),
                dto.comment()
        );
    }
}
