import {createGlobalStyle} from "styled-components";

export default createGlobalStyle`
  :root {
    --backgroundColorSecondary: #363237;
    --backgroundColorForms: #D09683;
    --borderColor: #73605B;
  }
  html,body {
    height: 100%;
    background-color: var(--backgroundColorSecondary);
    color: var(--backgroundColorForms);
  }
`