CREATE TABLE patients (
                          id UUID PRIMARY KEY,
                          full_name VARCHAR(150) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          birth_date DATE NOT NULL,
                          created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);