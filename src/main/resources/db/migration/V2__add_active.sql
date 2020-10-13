/*
한번 적용된 스크립트는 절대로 건들면 안된다.
V1__init.sql 을 한번 어플리케이션 실행 시 돌렸으면 V2, V3 이런식으로 신규 스크립트를 만들어줄 것
flyway 이 파일을 찾아서 다시 스크립트를 실행해주고, 그 다음에 hibernate가 돌아간다.
*/
ALTER TABLE account ADD COLUMN active BOOLEAN;