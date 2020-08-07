// Talkie library
// Copyright 2011 Peter Knight
// This code is released under GPLv2 license.
//
// Now for something a bit more challenging.
//
// Building sentences by program.
//
// Edited by h2in@iamamaker.kr(KoreanFemaleVoice.ino)


#include "talkie.h"


Talkie voice;


/* 안녕하세요. 아이엠어메이커에 오신것을 환영합니다. */
const uint8_t spHelloIamamaker[] PROGMEM = {0x90,0xB4,0xE4,0x23,0xD5,0xA8,0xEA,0xD0,0x42,0x73,0x27,0x95,0x2A,0x4D,0x35,0x31,0x1C,0x35,0xE6,0x04,0x15,0x96,0x6E,0x51,0xBF,0x8D,0x64,0xF0,0x95,0xB9,0x6A,0x0C,0x92,0xC0,0x27,0x8E,0xF3,0x70,0x44,0x00,0xBF,0x14,0xB2,0xAB,0x06,0x0E,0xD7,0x19,0x66,0x5B,0xDA,0x38,0x59,0x2A,0xD8,0xB4,0x6E,0xE1,0x74,0x37,0x53,0x97,0xA5,0x8E,0x47,0x4F,0x23,0x4C,0x97,0x28,0x0E,0x7E,0x37,0x4F,0xAD,0x53,0x58,0xDE,0xC2,0x34,0x6C,0x49,0xE3,0x6C,0x2E,0x27,0xD3,0xA5,0x85,0x93,0xB1,0x8A,0xD5,0x9E,0x18,0x1E,0xB7,0x1A,0x15,0x99,0xAB,0x84,0x20,0xB8,0x33,0xA4,0xAE,0xD2,0x8B,0xA4,0x08,0xE7,0xA8,0x41,0xC7,0xA9,0x9A,0x45,0x96,0x24,0x9B,0xC6,0x6A,0x14,0x9F,0x9C,0x5C,0x1E,0x23,0xC0,0x7D,0x4C,0xF1,0x5C,0x53,0x21,0xE7,0x39,0xC5,0xF3,0x83,0x4D,0x94,0x26,0x27,0xD7,0x0F,0x74,0x15,0x1D,0xE3,0x5C,0xD3,0xA1,0x8D,0x7D,0xAC,0xD2,0xF9,0xC0,0x75,0xCA,0xD1,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x60,0xD4,0xE4,0x3D,0xD5,0x30,0xEA,0x50,0x63,0xEE,0x54,0xC7,0x38,0x4D,0x0D,0x3D,0x94,0x8C,0xAB,0x34,0xD9,0x97,0x50,0x74,0x9F,0x52,0x44,0x6D,0x6A,0x48,0x6D,0x76,0x12,0xB8,0x9D,0x25,0x92,0xD5,0x8E,0xC7,0x69,0x8F,0x49,0x5F,0x3B,0x0E,0x95,0x3B,0x23,0x7E,0xE5,0x58,0x9C,0xEE,0x9C,0x78,0x6D,0x62,0xB9,0xE9,0x13,0x92,0x6D,0x85,0x75,0xAA,0x8E,0x19,0xB7,0x15,0xCE,0xE9,0x3A,0x61,0x68,0x97,0x38,0x1E,0x7B,0x50,0x64,0xA9,0x12,0xC0,0x6F,0xC7,0xB6,0x6A,0x8C,0x07,0x9F,0xAB,0x8B,0xEA,0x06,0x4E,0x6F,0x8A,0x32,0x9E,0x92,0x78,0xBB,0x29,0x42,0x65,0x72,0xE0,0xC5,0xE6,0x08,0x93,0xC9,0x48,0x04,0x5F,0xDC,0x8B,0xEA,0x28,0x1E,0xFC,0x2E,0x08,0xAF,0x97,0x78,0x96,0x66,0x90,0x75,0x69,0xE1,0xB5,0xAD,0x65,0xA4,0xA5,0x89,0x57,0xBA,0x4F,0x88,0xB6,0x06,0x81,0xAB,0x3E,0x25,0xDA,0x1A,0x04,0x66,0xE6,0x8C,0x68,0xAD,0x13,0xB0,0xDB,0x33,0xC2,0xB5,0x00,0xE0,0x38,0x13,0x25,0xD3,0xA5,0x6A,0xE1,0xCC,0x50,0x77,0xE3,0x39,0x85,0xB7,0xD3,0xD4,0x9C,0x96,0x16,0x4E,0xD5,0x0A,0x74,0xAF,0x52,0x38,0xEB,0x6A,0x89,0x65,0x71,0xE3,0x9C,0xAD,0x15,0xE6,0xC5,0x4D,0xB0,0x69,0x86,0x55,0xE6,0x04,0x49,0xED,0x09,0x4A,0xAD,0x4A,0x64,0xF1,0xB1,0x36,0xA0,0x89,0x90,0xF9,0xC7,0x1C,0x87,0x36,0x4A,0xE4,0x0F,0xAB,0x15,0xD6,0x38,0x91,0x3C,0x11,0x1B,0x88,0x17,0x04,0xCB,0x6A,0xBB,0x49,0x4E,0x10,0x86,0x20,0xF1,0x8D,0xA4,0x08,0xD8,0x5E,0xC8,0xD0,0x5C,0xDF,0x95,0x43,0x32,0xC5,0xC0,0xBF,0x0D,0x96,0x63,0x08,0x03,0xBF,0x0B,0x9E,0x45,0xC3,0x2C,0xE6,0x40,0x71,0xB6,0x6A,0x65,0x50,0xE9,0xC2,0xDA,0x16,0x19,0x03,0x71,0x1B,0x6F,0x4B,0x65,0x0E,0xC4,0x29,0x82,0x2E,0x51,0xEE,0xD0,0xC4,0x08,0x78,0x46,0x8B,0xF0,0x3C,0x21,0x59,0x25,0x15,0x29,0x75,0x21,0x30,0xCA,0xC4,0xA6,0x63,0xDD,0xC6,0x38,0xE9,0x5B,0x05,0xE9,0x1A,0x13,0xB8,0x6F,0x35,0x38,0x2B,0xB4,0x96,0xB5,0x51,0x98,0xDC,0xD1,0x78,0x6F,0x4B,0x70,0x33,0x41,0xD1,0x52,0x9D,0xC9,0x71,0x95,0x1D,0xA3,0xB9,0x19,0x5A,0x46,0x40,0x2E,0x91,0x8D,0x4C,0xD1,0xC3,0x8C,0x93,0x36,0xCA,0xA4,0x08,0x71,0x9F,0xE3,0x68,0xFC,0x2A,0x39,0x6A,0x32,0xA2,0xD1,0x2F,0x85,0xA8,0x70,0x8E,0xC2,0x7B,0x57,0xD8,0x97,0x36,0x5A,0x94,0x4E,0x36,0x9F,0xDB,0x18,0xD3,0xDC,0xD4,0x65,0x49,0x62,0xF8,0x90,0x70,0xB5,0xDB,0x8C,0x85,0xDF,0xCD,0x4A,0xEB,0x06,0x86,0xCC,0x4A,0x4F,0x5B,0xDA,0x58,0x59,0xC2,0xC4,0x63,0x6E,0x13,0x44,0x75,0x93,0xB4,0x39,0x4E,0x02,0x2F,0xCC,0x5B,0xE6,0x20,0x11,0x7C,0x65,0x5E,0x59,0x8A,0x64,0xF0,0x99,0xF5,0x3C,0x1E,0x20,0x15,0xFC,0x62,0xD8,0x29,0xCB,0x34,0xF0,0x4B,0xA0,0x77,0xB2,0x30,0xC1,0x0F,0x85,0x98,0xCB,0x28,0x04,0x2F,0x0C,0x34,0xAA,0x86,0x40,0xFB,0x68,0x12,0xAB,0xD3,0x42,0x9B,0xDC,0x40,0xBD,0x4A,0x0B,0x83,0x35,0x27,0xA1,0xA5,0x2D,0x4C,0x4E,0x9C,0x94,0xE2,0xB5,0xB0,0x04,0x36,0x31,0x88,0xFE,0xFF};

/* 안녕하세요. Talkie 라이브러리를 사용한 음성 출력 예제 입니다. */
const uint8_t spTalkieExample[] PROGMEM = {0x50,0xB4,0xE4,0x23,0xD5,0xA8,0xEA,0xD0,0x42,0x73,0x27,0x95,0x2A,0x4D,0x35,0x31,0x1C,0x35,0xE6,0x24,0x15,0xE6,0x6E,0x51,0xBF,0x8D,0x64,0xF0,0x8D,0xA9,0x6A,0x0C,0x10,0x01,0xFC,0x52,0xC8,0xAE,0x1A,0x38,0x5C,0x67,0x98,0x6D,0x69,0xE3,0x64,0xA9,0x60,0xD3,0xBA,0x85,0xD3,0xDD,0x4C,0x5D,0x96,0x3A,0x1E,0x3F,0x8D,0x30,0x5D,0xA2,0x38,0xF8,0xDD,0x3C,0xB5,0x4E,0x61,0x79,0x0B,0xD3,0xB0,0x25,0x8D,0xB3,0xB9,0x9C,0x54,0x97,0x16,0x4E,0xC6,0x2A,0x56,0x7D,0x62,0x78,0xD4,0x7A,0x54,0x64,0xAE,0x10,0x82,0xE0,0xCE,0x90,0xBA,0x42,0xCF,0x92,0x22,0x9C,0xA2,0x06,0x1D,0xA7,0x6A,0x16,0x59,0x12,0x6C,0x1A,0xAB,0x51,0x7C,0x72,0x72,0x79,0x8C,0x00,0xF7,0x31,0xC5,0x73,0x4D,0x85,0x9C,0xE7,0x14,0xCF,0x0F,0x36,0x51,0x9A,0x9C,0x5C,0x3F,0xD0,0x55,0x74,0x8C,0x73,0xCD,0x80,0x36,0xF6,0xB1,0x42,0xE7,0x03,0xD7,0x29,0x46,0x03,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x18,0x5A,0x86,0xC8,0x28,0x89,0x66,0x68,0xFA,0xB1,0xD2,0x75,0xAE,0x62,0xF0,0xA7,0xEA,0xC0,0xA7,0x00,0x00,0x86,0xA1,0xF1,0xB6,0x94,0x92,0x39,0x9A,0xE4,0xBB,0x64,0x5A,0x1D,0x68,0xE6,0xF6,0x4A,0x71,0x8D,0x63,0x49,0xD8,0x0B,0xC1,0x75,0x46,0x80,0x6D,0xDA,0x8D,0x9B,0x34,0x49,0x87,0x48,0x51,0xAD,0xB3,0xA4,0x90,0x23,0x49,0x64,0xF2,0x96,0x86,0xE8,0x7D,0x25,0xAA,0xCD,0x2E,0x82,0xD2,0xB5,0x2C,0xBA,0x38,0xF1,0xDC,0xCC,0x09,0xF1,0xEB,0xC0,0x31,0x3B,0x17,0x4C,0x6B,0x1D,0x87,0xD3,0x9E,0x31,0x2D,0x43,0x3C,0xF8,0x9B,0xE0,0x12,0xCD,0xF0,0xB8,0x57,0x59,0x4A,0xD4,0x20,0xE0,0x51,0x25,0x4E,0xF5,0x92,0x20,0x7C,0x74,0x06,0x24,0x1B,0xBC,0x4B,0xD1,0x99,0x10,0x77,0x08,0xBE,0x78,0x85,0xC3,0xD2,0x26,0xD8,0xEA,0x69,0x8A,0x4B,0x8B,0xC4,0x6B,0x95,0x1A,0xD6,0x35,0x22,0x8A,0x73,0x21,0xD0,0xDE,0x89,0xC4,0xF6,0x85,0x40,0xF7,0x28,0x19,0x09,0x85,0xBE,0x0A,0x48,0xD6,0xB8,0xC8,0x64,0x7A,0x41,0xFE,0x60,0x33,0xE5,0xA9,0x41,0xE9,0x8D,0x49,0x4C,0xA6,0x1A,0x94,0x17,0xC6,0x49,0xDF,0x0A,0x48,0xEF,0x28,0x5A,0xE6,0x52,0x68,0xBD,0x20,0x85,0xAB,0x0D,0xB7,0x2A,0x0C,0x4B,0x82,0x42,0x95,0x23,0x43,0xA3,0xAB,0xF3,0x11,0xE5,0xB8,0x83,0xF6,0x3E,0x1A,0x25,0x67,0x0F,0x46,0xB7,0x2A,0xE2,0x98,0xDC,0x58,0x35,0xC3,0xD8,0x6C,0x72,0xE1,0xE4,0x51,0x37,0x93,0xD9,0x81,0xE7,0x87,0xD3,0x45,0x26,0x1B,0x81,0x3C,0x2A,0x57,0x9D,0x4D,0x04,0xF0,0x49,0x73,0xB8,0x5D,0x60,0xE9,0x08,0xD7,0xB4,0xA5,0x83,0xB5,0x39,0x9C,0xCC,0x97,0x0C,0xD6,0xC7,0x48,0x12,0x7B,0x92,0x58,0x96,0x6A,0x54,0xFC,0x31,0xE2,0xE0,0x0F,0xA5,0xAA,0x31,0x84,0x07,0x3F,0x84,0x2A,0xA7,0x22,0x09,0xBC,0x36,0x30,0x99,0x63,0x14,0x3C,0x26,0xC9,0xA8,0x5E,0x50,0xE8,0xEA,0x00,0xE7,0x7A,0x46,0x85,0xAF,0x14,0x8A,0xEA,0x11,0x05,0xFC,0x52,0x58,0xAB,0x4F,0x24,0xF0,0x4D,0x68,0x7C,0x0E,0x13,0xC0,0xAF,0x16,0x95,0x3A,0x4C,0x50,0xDA,0x3A,0x53,0xEA,0x28,0xD6,0x59,0xAF,0x30,0xAE,0xDB,0x68,0x5B,0x35,0xCC,0x64,0x71,0xA3,0xFD,0x64,0x57,0xB1,0xDB,0x81,0xE1,0x9B,0x32,0x23,0xA6,0x30,0x06,0x7E,0x95,0x18,0x4D,0x86,0x18,0xF8,0x85,0xED,0x34,0x29,0x00,0x20,0x60,0x70,0x0E,0xC7,0x30,0x3F,0xE3,0x89,0xF1,0x02,0x8B,0x47,0x8F,0x26,0x39,0x0F,0x1C,0x6A,0xB3,0x99,0x64,0xDF,0x71,0x70,0xED,0xA5,0x61,0x72,0xC5,0x83,0xB7,0xAB,0xC4,0xED,0x1C,0x87,0xDA,0x2C,0xB3,0xAE,0x6D,0x9C,0x4E,0xDD,0x24,0x32,0x67,0x70,0xB6,0x54,0xB0,0xCB,0x9C,0xC6,0xDA,0x69,0x6A,0x2E,0x4B,0x0C,0xC7,0xAE,0x58,0x84,0xCE,0x01,0x00,0xA1,0x80,0x7B,0x03,0x92,0xAF,0x9C,0x8C,0xF2,0x2E,0x90,0x7F,0x0B,0x32,0x0F,0xB3,0x08,0xBC,0x2D,0x2A,0x4C,0x05,0x6F,0x1A,0xC9,0xDE,0x0A,0x91,0xD9,0xD8,0x0C,0xAD,0x12,0x38,0xA5,0x62,0x3B,0xA1,0x5E,0x63,0x8D,0x8A,0x4B,0xC1,0xF6,0x83,0x0D,0xCA,0xCF,0x08,0xDB,0x0D,0x2E,0xA8,0x3A,0x63,0x68,0xDB,0x84,0x28,0xE3,0x92,0x21,0xAD,0x50,0x54,0xD8,0x53,0xC0,0xB5,0x4A,0x65,0x65,0x07,0x40,0x5F,0x1B,0x05,0xB4,0x1B,0xE0,0x7C,0x4D,0x54,0xF0,0x4B,0x61,0xAB,0x3A,0xD2,0xC0,0x27,0x96,0xF7,0x7A,0x48,0x07,0x1F,0xC5,0xDF,0xE3,0x01,0x32,0xC1,0x0F,0x86,0xDD,0xC9,0xC4,0x02,0xBF,0x04,0x62,0x27,0x23,0x1B,0xFC,0x60,0xC8,0x19,0x2D,0xDC,0xA0,0xB5,0x44,0xAD,0x72,0x0A,0x5C,0xF4,0x02,0xF1,0x32,0x25,0x0C,0xC9,0x0D,0xC4,0xAA,0x94,0x30,0x38,0x75,0x54,0x6A,0x96,0xC2,0xEC,0xC4,0x59,0x31,0x7E,0x08,0x93,0x65,0x67,0xE3,0x78,0xFF};

void setup() {

}

void loop() {

  voice.say(spHelloIamamaker);
  delay(500);
  voice.say(spTalkieExample);
  delay(2000);

}
