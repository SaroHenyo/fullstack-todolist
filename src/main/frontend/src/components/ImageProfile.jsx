import React, { useCallback, useEffect, useState } from 'react'
import './ImageProfile.css'
import axios from 'axios'
import { useDropzone } from 'react-dropzone'

function UserProfiles() {
  const [userProfiles, setUserProfiles] = useState([])

  const fetchUserProfiles = () => {
    axios.get('http://localhost:8080/image/getUserProfiles').then((res) => {
      setUserProfiles(res.data)
    })
  }

  useEffect(() => {
    fetchUserProfiles()
  }, [])

  return userProfiles.map((userProfile, index) => {
    return (
      <div className="col-md-6" key={index}>
        {userProfile.userProfileId ? (
          <img
            src={`http://localhost:8080/image/${userProfile.userProfileId}/downloadImage`}
          />
        ) : null}
        <br />
        <br />
        <h1>{userProfile.username}</h1>
        <p>{userProfile.userProfileId}</p>
        <MyDropzone {...userProfile} />
        <br />
        <br />
      </div>
    )
  })
}

function MyDropzone({ userProfileId }) {
  const onDrop = useCallback((acceptedFiles) => {
    const file = acceptedFiles[0]

    console.log('FILE', file)
    console.log('userProfileId', userProfileId)

    const formData = new FormData()
    formData.append('file', file)

    axios
      .put(
        `http://localhost:8080/image/uploadImage/${userProfileId}`,
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        },
      )
      .then(() => {
        console.log('file uploaded successfully')
        window.location.reload()
      })
      .catch((err) => {
        console.log(err)
      })
  }, [])
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop })

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {isDragActive ? (
        <p>Drop the files here ...</p>
      ) : (
        <p>Drag 'n' drop some files here, or click to select files</p>
      )}
    </div>
  )
}

function ImageProfile() {
  return (
    <div className="App">
      <div className="row" style={{ marginTop: '100px' }}>
        <UserProfiles />
      </div>
    </div>
  )
}

export default ImageProfile
