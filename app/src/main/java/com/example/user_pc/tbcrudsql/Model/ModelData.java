package com.example.user_pc.tbcrudsql.Model;

public class ModelData {
    String npm,nama,prodi,fakultas,
    nisn,nsiswa,pilprodi,piljurusan,jumlah,
    nip,nama_dosen,prodi_dosen,matkul_dosen,
    id_matkul,matkul,prodi_matkul,dosen_matkul;

    public ModelData(){}

    public ModelData(String npm, String nama, String prodi, String fakultas,
                     String nisn, String nsiswa, String pilprodi, String piljurusan, String jumlah,
                     String nip, String nama_dosen, String prodi_dosen, String matkul_dosen,
                     String id_matkul, String matkul, String prodi_matkul, String dosen_matkul) {
        this.npm = npm;
        this.nama = nama;
        this.prodi = prodi;
        this.fakultas = fakultas;

        this.nisn = nisn;
        this.nsiswa = nsiswa;
        this.pilprodi = pilprodi;
        this.piljurusan = piljurusan;
        this.jumlah = jumlah;

        this.nip = nip;
        this.nama_dosen = nama_dosen;
        this.prodi_dosen = prodi_dosen;
        this.matkul_dosen = matkul_dosen;

        this.id_matkul = id_matkul;
        this.matkul = matkul;
        this.prodi_matkul = prodi_matkul;
        this.dosen_matkul = dosen_matkul;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }


    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getNsiswa() {
        return nsiswa;
    }

    public void setNsiswa(String nsiswa) {
        this.nsiswa = nsiswa;
    }

    public String getPilprodi() {
        return pilprodi;
    }

    public void setPilprodi(String pilprodi) {
        this.pilprodi = pilprodi;
    }

    public String getPiljurusan() {
        return piljurusan;
    }

    public void setPiljurusan(String piljurusan) {
        this.piljurusan = piljurusan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }


    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }

    public String getProdi_dosen() {
        return prodi_dosen;
    }

    public void setProdi_dosen(String prodi_dosen) {
        this.prodi_dosen = prodi_dosen;
    }

    public String getMatkul_dosen() {
        return matkul_dosen;
    }

    public void setMatkul_dosen(String matkul_dosen) { this.matkul_dosen = matkul_dosen; }


    public String getId_matkul() {
        return id_matkul;
    }

    public void setId_matkul(String id_matkul) {
        this.id_matkul = id_matkul;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getProdi_matkul() {
        return prodi_matkul;
    }

    public void setProdi_matkul(String prodi_matkul) {
        this.prodi_matkul = prodi_matkul;
    }

    public String getDosen_matkul() {
        return dosen_matkul;
    }

    public void setDosen_matkul(String dosen_matkul) { this.dosen_matkul = dosen_matkul; }

}
