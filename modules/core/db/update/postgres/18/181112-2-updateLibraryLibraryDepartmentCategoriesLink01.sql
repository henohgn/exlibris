alter table LIBRARY_LIBRARY_DEPARTMENT_CATEGORIES_LINK add constraint FK_LIBRARY_DEPARTMENT_CATEGORIES_LINK_ON_LIBRARY_DEPARTMENT foreign key (LIBRARY_DEPARTMENT_ID) references LIBRARY_LIBRARY(ID);