import styled from 'styled-components';
import { border } from '../../common/utils/enum/border';
import { borderRadius } from '../../common/utils/enum/borderRadius';
import { colorPalette } from '../../common/utils/enum/colorPalette';
import { fontSize } from '../../common/utils/enum/fontSize';
export const UploadContainer = styled.div`
  display: flex;
`;
export const UploadImageLabel = styled.label`
  width: 9.875rem;
  height: 7.875rem;
  border: ${border.basic};
  border-radius: ${borderRadius.basicRadius};
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: ${colorPalette.CaemeraIconColor};
  cursor: pointer;
  & svg {
    width: 2.563rem;
    height: 2.313rem;
    transform: translateY(0.2rem);
  }
`;
export const UploadImageWrapper = styled.input`
  display: none;
`;
export const UploadImageCountWrapper = styled.div`
  font-size: ${fontSize.basic};
  transform: translateY(-0.1rem);
`;