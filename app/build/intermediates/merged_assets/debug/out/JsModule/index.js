import { log } from "mathjs";

export const getMessage = () => {
  return "This is message from Javascript side";
};

export class MathJs {
  static log(x, base) {
    const result = log(x, base);
    return JSON.stringify(result);
  }
}
