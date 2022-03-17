package com.ssafy.tourist.domain.record.service;

import com.ssafy.tourist.domain.course.db.repository.CourseDataRepository;
import com.ssafy.tourist.domain.record.db.entity.Tour;
import com.ssafy.tourist.domain.record.db.entity.TourID;
import com.ssafy.tourist.domain.record.db.entity.TourStamp;
import com.ssafy.tourist.domain.record.db.entity.TourStampID;
import com.ssafy.tourist.domain.record.db.repository.TourRepository;
import com.ssafy.tourist.domain.record.db.repository.TourStampRepository;
import com.ssafy.tourist.domain.record.request.TourEndPostReq;
import com.ssafy.tourist.domain.record.request.TouristVisitPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    TourStampRepository tourStampRepository;

    @Autowired
    CourseDataRepository courseDataRepository;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    public int courseStartByUser(int userId, int courseId) {
        // 코스 시작
        Tour tour = new Tour();

        tour.setUserId(userId);
        tour.setCourseId(courseId);
        tour.setTourStart(LocalDateTime.now());
        tour.setStart(true);

        tourRepository.save(tour); // 코스 시작 정보 넣기

        // ** 코스 시작 이후 관광지 방문 db도 처리
        TourStamp tourStamp = new TourStamp();

        // 코스 번호 받아서 코스 안에 관광지 개수 카운트 -> 관광지 개수만큼 stamp에 넣기
        int touristCount = courseDataRepository.courDataCount(courseId);

        for (int i = 1; i <= touristCount; i++) {
            tourStamp.setCourseId(courseId);
            tourStamp.setCourseDataId(i);
            tourStamp.setUserId(userId);

            tourStampRepository.save(tourStamp);
        }

        return SUCCESS;
    }

    @Override
    public int courseEndByUser(TourEndPostReq tourEndPostReq) {
        TourID tourID = new TourID();
        tourID.setUserId(tourEndPostReq.getUserId());
        tourID.setCourseId(tourEndPostReq.getCourseId());

        if(tourRepository.findById(tourID).isPresent()) {
            return tourRepository.tourEndByUser(tourEndPostReq.getUserId(), tourEndPostReq.getCourseId());
        } else return FAIL;
    }

    @Override
    public int touristVisitByUser(TouristVisitPostReq touristVisitPostReq) {
        TourStampID tourStampID = new TourStampID();
        tourStampID.setUserId(touristVisitPostReq.getUserId());
        tourStampID.setCourseId(touristVisitPostReq.getCourseId());
        tourStampID.setCourseDataId(touristVisitPostReq.getCourseDataId());

        if(tourStampRepository.findById(tourStampID).isPresent()) {
            return tourStampRepository.touristVisitByUser(touristVisitPostReq.getUserId(), touristVisitPostReq.getCourseId(), touristVisitPostReq.getCourseDataId());
        }
        return FAIL;
    }
}