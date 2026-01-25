package pal.comp.pgsbackend.services;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pal.comp.pgsbackend.dto.report.RequestByFilterDto;
import pal.comp.pgsbackend.dto.report.RequestCreateReportDto;
import pal.comp.pgsbackend.dto.report.ResponseReportDto;
import pal.comp.pgsbackend.mapper.ReportMapper;
import pal.comp.pgsbackend.repository.ReportRepository;

import java.util.List;

@Service
public class ReportService {
    private static final Logger log = LoggerFactory.getLogger(ReportService.class);
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;


    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    public List<ResponseReportDto> getAllByFilter(RequestByFilterDto requestFilter) {
        log.info("Get reports by filter = {}" , requestFilter.toString());
        return reportRepository.getReportsByFilter(
                requestFilter.planId(),
                requestFilter.plotId(),
                requestFilter.typeWorkId(),
                requestFilter.subtypeWorkId(),
                requestFilter.productionName(),
                requestFilter.startDate(),
                requestFilter.endDate(),
                requestFilter.constDate()
        ).stream().map(reportMapper::toDto).toList();
    }

    @Transactional
    public ResponseReportDto create(RequestCreateReportDto reportToCreate) {
        log.info("Create report = {}", reportToCreate.toString());
        var reports = reportRepository.getReportsByFilter(
                reportToCreate.planId(),
                null,
                null,
                null,
                null,
                null,
                null,
                reportToCreate.date()
        );
        if (!reports.isEmpty()) {
            log.info("Такой отчёт найден");
            var report =  reports.getFirst();
            report.setWhoSend(reportToCreate.whoSend());
            report.setFact(reportToCreate.fact());
            report.setMachine(
                    reportToCreate.machine() != null ? reportToCreate.machine() : report.getMachine()
            );
            report.setComment(
                    reportToCreate.comment() != null ? reportToCreate.comment() : report.getComment()
            );
            var updatedReport = reportRepository.save(report);
            this.reportRepository.resetDelta(report.getPlan().getId());
            return reportMapper.toDto(updatedReport);
        }
        var reportEntity = reportMapper.toEntity(reportToCreate);
        var createdReport = reportRepository.save(reportEntity);
        this.reportRepository.resetDelta(createdReport.getPlan().getId());
        return reportMapper.toDto(createdReport);
    }




}
