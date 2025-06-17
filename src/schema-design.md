CREATE TABLE patients (
  patient_id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  date_of_birth DATE,
  phone VARCHAR(20),
  email VARCHAR(100),
  address VARCHAR(255)
);

CREATE TABLE medical_records (
  record_id INT PRIMARY KEY AUTO_INCREMENT,
  patient_id INT,
  record_date DATE,
  diagnosis TEXT,
  treatment TEXT,
  FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

-- Example updated appointments table to include patient_id FK:
CREATE TABLE appointments (
  appointment_id INT PRIMARY KEY AUTO_INCREMENT,
  doctor_id INT,
  patient_id INT,
  appointment_date DATETIME,
  status VARCHAR(20),
  FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
  FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);
