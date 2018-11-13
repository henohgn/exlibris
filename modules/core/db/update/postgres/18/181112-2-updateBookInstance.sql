alter table LIBRARY_BOOK_INSTANCE rename column library_department_id to library_department_id__u18827 ;
drop index IDX_LIBRARY_BOOK_INSTANCE_LIBRARY_DEPARTMENT ;
alter table LIBRARY_BOOK_INSTANCE add column LIBRARY_DEPARTMENT_ID uuid ;
