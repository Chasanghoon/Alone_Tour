package com.ssafy.tourist.domain.record.db.repository;

import com.ssafy.tourist.domain.record.db.entity.TourStamp;
import com.ssafy.tourist.domain.record.db.entity.TourStampID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TourStampRepository extends JpaRepository<TourStamp, TourStampID> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update TourStamp t set t.isStamp = 1 where t.userId = :userId and t.courseId = :courseId and t.courseDataId = :courseDataId")
    int touristVisitByUser(int userId, int courseId, int courseDataId);

    @Query("select count(s) from TourStamp s where s.isStamp = 1 and s.userId = :userId and s.courseId = :courseId")
    int isStampByuserIdandCourseId(int userId, int courseId);
}
