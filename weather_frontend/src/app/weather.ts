export interface Weather {
  city_name: string,
  country_code: string,
  description: string,
  rain: number,
  snow: number,
  temperature: {
    max: number,
    min: number,
    temp: number
  },
  weather_icon: string
}