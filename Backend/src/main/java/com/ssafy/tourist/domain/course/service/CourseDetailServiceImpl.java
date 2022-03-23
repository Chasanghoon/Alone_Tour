package com.ssafy.tourist.domain.course.service;

import com.ssafy.tourist.domain.course.db.bean.TourTestResult;
import com.ssafy.tourist.domain.course.db.entity.CourseData;
import com.ssafy.tourist.domain.course.db.repository.CourseDetailRepositorySpp;
import com.ssafy.tourist.domain.course.db.repository.CourseRepository;
import com.ssafy.tourist.domain.record.db.entity.Record;
import com.ssafy.tourist.domain.tourtest.db.entity.TourTest;
import com.ssafy.tourist.domain.tourtest.db.repository.TourTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailServiceImpl implements CourseDetailService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TourTestRepository tourTestRepository;

    @Autowired
    CourseDetailRepositorySpp courseDetailRepositorySpp;


    @Override
    public List<CourseData> courseDataDetail(int courseId) { return courseDetailRepositorySpp.courseDataDetailByCourseId(courseId); }

    @Override
    public boolean courseIsRegister(int courseId) { return courseRepository.findCourseIsRegisterByCourseId(courseId); }

    @Override
    public List<Record> courseRecordDetail(int courseId) { return courseDetailRepositorySpp.courseRecordDetailByCourseId(courseId); }

    @Override
    public List<TourTestResult> courseTourTestResultDetail(int courseId) { return courseDetailRepositorySpp.courseTourTestResultDetailByCourseId(courseId); }
}
