CREATE TABLE time_tracking (
  id UUID NOT NULL,
   type VARCHAR(20) NOT NULL,
   activity VARCHAR(20),
   sub_activity VARCHAR(20),
   start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   end_time TIMESTAMP WITHOUT TIME ZONE,
   description VARCHAR(100),
   CONSTRAINT pk_time_tracking PRIMARY KEY (id)
);