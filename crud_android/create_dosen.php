<?php

    include_once('connection.php');

    $nip             =$_POST['nip'];
    $nama_dosen      =$_POST['nama_dosen'];
    $prodi_dosen     =$_POST['prodi_dosen'];
    $matkul_dosen    =$_POST['matkul_dosen'];

    $insert = "INSERT INTO tb_dosen(nip,nama_dosen,prodi_dosen,matkul_dosen)
               VALUES ('$nip','$nama_dosen','$prodi_dosen','$matkul_dosen')";

    $exeinsert = mysqli_query($koneksi,$insert);

    $response = array();

    if($exeinsert)
    {
        $response['code'] =1;
        $response['message'] = "Data Berhasil Ditambahkan !";
    }
    else
    {
        $response['code'] =0;
        $response['message'] = "Data Gagal Ditambahkan !";
    }

        echo json_encode($response);
?>