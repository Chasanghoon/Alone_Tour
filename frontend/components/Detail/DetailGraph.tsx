import dynamic from 'next/dynamic';
import React from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '../../store';
import { GraphBlock, GraphPercentage, GraphPie } from './Datail.style';

const CourseTypeChart = dynamic(() => import('../Charts/CourseTypeChart'), {
  ssr: false,
});
export default function DetailGraph() {
  const { coursePercentage } = useSelector((state: RootState) => state.detail);
  return (
    <GraphBlock>
      <GraphPercentage>
        <div>
          π μλ‘μ΄ μΈμ°μ λ§λ  νλ₯  <label>{coursePercentage}</label>%
        </div>
        <div className="percentage">
          μ¬ν μ€(60%) + κ΄κ΄μ§ λ°©λ¬Έ(25%) + λΆλ§ν¬(15%)
        </div>
      </GraphPercentage>
      <GraphPie>
        <div className="pie-title">μ¬ν μ±ν₯ νμ€νΈ μ νλ³ μ¬ν λΉμ¨</div>
        <CourseTypeChart />
      </GraphPie>
    </GraphBlock>
  );
}
