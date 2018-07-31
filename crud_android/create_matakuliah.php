<?php

    include_once('connection.php');

    $id_matkul           =$_POST['id_matkul'];
    $matkul              =$_POST['matkul'];
    $prodi_matkul        =$_POST['prodi_matkul'];
    $dosen_matkul        =$_POST['dosen_matkul'];
    

    $insert = "INSERT INTO tb_matakuliah(id_matkul,matkul,prodi_matkul,dosen_matkul) 
    VALUES ('$id_matkul','$matkul','$prodi_matkul','$dosen_matkul')";

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