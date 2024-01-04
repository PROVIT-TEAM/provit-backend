import React from 'react'
import styled from 'styled-components';


interface StyledSelectProp {
    width?: string;
    height?: string;
};
const StyledSelect = styled.select<StyledSelectProp>`
    width: ${(props) => props.width};
    height: ${(props) => props.height || '48px'};
    background-color: #3A3A3C;
    color: #8E8E93;
    font-weight: bold;
    padding: 8px;
    padding-right: 20px;
    padding-right: 20px;
    border: none;
    border-radius: 6px;
    margin: 0px 5px;
`;
const StyledSelectForm = styled.div`
    //select 화살표 못생김
    //-webkit-appearance:none; /* for chrome */
    //-moz-appearance:none; /*for firefox*/
    //appearance:none;
`
interface DateFields {
    year: string;
    month: string;
    day: string;
}
interface DatePickerProps {
    values: DateFields;
    onChange: (field: keyof DateFields, value: string) => void;
}

const getYearOptions = () => {
    const years: number[] = [];
    for (let year = 2024; year >= 1950; year--) {
        years.push(year);
    }
    return years;
    };

const DatePicker: React.FC<DatePickerProps> = ({values, onChange}) => {
    const years: number[] = getYearOptions(); // 년도 옵션
    const months = Array.from({ length: 12 }, (_, index) => index + 1); // 1부터 12까지의 월 옵션
    const days = Array.from({ length: 31 }, (_, index) => index + 1); // 1부터 31까지의 일 옵션
    const SelectHandler = (field: keyof DateFields, selectValue: string) => {
        onChange(field, selectValue);
    }

    return (
        <StyledSelectForm> 
        <StyledSelect
            width='96px' id="year" name="year"
            value={values.year} onChange={(e) => SelectHandler('year', e.target.value)}
        >
            <option value="">년</option>
            {years.map((year) => (
            <option key={year} value={year}>
                {year}년
            </option>
            ))}
        </StyledSelect>
        
        <StyledSelect
            width='90px' id="month" name="month"
            value={values.month} onChange={(e) => SelectHandler('month', e.target.value)}
        >
            <option value="">월</option>
            {months.map((month) => (
            <option key={month} value={month}>
                {month}월
            </option>
            ))}
        </StyledSelect>

        <StyledSelect
            width='90px' id="day" name="day"
            value={values.day} onChange={(e) => SelectHandler('day', e.target.value)}
        >
            <option value="">일</option>
            {days.map((day) => (
            <option key={day} value={day}>
                {day}일
            </option>
            ))}
        </StyledSelect>
        </StyledSelectForm>
    );
    };

export default DatePicker