create table tape(  				--테입
t_n number primary key not null,	--테입 번호
t_name nvarchar2(10) ,				--테입 제목
t_kind nvarchar2(20) 				--테입 종류
);

create table tape(  			
t_n number primary key not null,	
t_name nvarchar2(10) ,				
t_kind nvarchar2(20) 				
);

select * from tape
drop table tape

create table customer(					--회원
m_n number primary key not null,	--회원번호
m_name nvarchar2(20),				--회원 성명
m_add nvarchar2(20),				--회원 주소
m_tel number						--회원 전화번호
);
create table customer(				
m_n number primary key not null,	
m_name nvarchar2(20),				
m_add nvarchar2(20),				
m_tel number						
);
select * from mer

create or replace procedure customer_insert(
p_name in mer.m_name%type,
p_add in mer.m_add%type,
p_tel in mer.m_tel%type
)
is
begin
	insert into customer
	values((select nvl(max(m_n),0)+1 from customer), p_name, p_add, p_tel);
	commit;
	end;
	/

create table rental(				--대여
r_n number primary key not null,	--대여번호
m_n number,							--회원 번호(mer꺼)
t_n number,							--테입 번호(tape꺼)
r_date number						--대여날짜(자기꺼)
constraint FK_C foreign key(m_n) references customer(m_n) on delete cascade,
constraint FK_T foreign key(t_n) references tape(t_n) on delete cascade,
);

create table rental(				
r_n number primary key,	
m_n number,							
t_n number,							
r_date number						
constraint FK_C foreign key(m_n) references customer(m_n) ON DELETE CASCADE,
constraint FK_T foreign key(t_n) references tape(t_n)  ON DELETE CASCADE
);


create table RENT (
 r_no number primary key,
 c_no number,
 t_no number,
 r_date date,
CONSTRAINT FK_C FOREIGN KEY(c_no) REFERENCES CUSTOMER(c_no) ON DELETE CASCADE,
CONSTRAINT FK_T FOREIGN KEY(t_no) REFERENCES TAPE(t_no) ON DELETE CASCADE
);

create or replace procedure tape_insert(
    p_t_n in tape.t_n%type,
    p_t_name in tape.t_name%type
    )
    is
    begin
    insert into tape
    values((select nvl(max(t_n),0)+1 from tape),p_t_n, p_t_name);
    commit;
   end;
   /


create or replace procedure customer_insert(
p_name in mer.m_name%type,
p_add in mer.m_add%type,
p_tel in mer.m_tel%type
)
is
begin
	insert into mer
	values((select nvl(max(m_n),0)+1 from mer), p_name, p_add, p_tel);
	commit;
	end;
	/
	




create table rental(				--대여
r_n number primary key not null,	--대여번호
m_n number,							--회원 번호(mer꺼)
t_n number,							--테입 번호(tape꺼)
r_date number						--대여날짜(자기꺼)


create or replace procedure rental_insert(
p_m_n in mer.m_n%type,
p_t_n in tape.t_n%type
)
is
begin
	insert into rental
	values ((select nvl(max(r_n),0)+1 from rental), p_m_n, p_t_n, sysdate);
	commit;
end;
/
drop table rental

create table rental(				
r_n number primary key not null,	
m_n number,							
t_n number,						
r_date number						
)



create or replace procedure rental_insert(
p_m_n in mer.m_n%type,
p_t_n in tape.t_n%type
)
is
begin
	insert into rental
	values ((select nvl(max(r_n), 0)+1 from rental), p_m_n, p_t_n, sysdate);
	commit;
end;
/







create or replace procedure rental









