import type { NextPage } from 'next';
import SearchForm from './SearchForm';
import Nav from '../Nav/Nav';
import { Wrapper, CourseBlock } from './CourseComp.style';
import CourseList from './CourseList';
import Footer from '../Footer/Footer';
import Menu from '../Nav/Menu';

const CourseComp: NextPage = () => {
  return (
    <>
      <Nav />
      <Menu currentName="코스추천" />
      <Wrapper>
        <SearchForm />
        <CourseBlock>
          <div className="title">OOO님 맞춤 추천 서비스</div>
          <CourseList />
          <div className="title">푸른 자연을 느낄 수 있는 코스</div>
          <CourseList />
          <div className="title">역사가 담긴 코스</div>
          <CourseList />
          <div className="title">시원한 바다의 향기가 느껴지는 코스</div>
          <CourseList />
        </CourseBlock>
      </Wrapper>
      <Footer />
    </>
  );
};

export default CourseComp;