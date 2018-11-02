alter table LIBRARY_FILES add constraint FK_LIBRARY_FILES_ON_FILE foreign key (FILE_ID) references SYS_FILE(ID);
create index IDX_LIBRARY_FILES_ON_FILE on LIBRARY_FILES (FILE_ID);
