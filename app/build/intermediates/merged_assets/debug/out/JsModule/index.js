import { log } from "mathjs";

export const getMessage = () => {
  return "This is message from Javascript side";
};

export class MathJs {
  static log(x, base) {
    return log(x, base);
  }

  static doublePoint(inputPoint) {
    const result = { lat: 2 * inputPoint.lat, lon: 2 * inputPoint.lon };
    return JSON.stringify(result);
  }
}
