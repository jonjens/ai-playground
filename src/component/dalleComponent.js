import React from 'react';

function ImageUpload() {
    const uploadImage = () => {
        const fileInput = document.getElementById('imageFileInput').files[0];
        const formData = new FormData();
        formData.append('file', fileInput);

        fetch('/dalle', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json()) // Parse the JSON response
            .then(data => {
                const imageUrl = data.data[0].url; // Extract the image URL from the JSON response

                // Update the image source
                document.getElementById('variationImage').src = imageUrl;
            })
            .catch(error => console.error('Error:', error));
    };

    return (
        <div>
            <h1>DALL-E Image Variation</h1>
            <form id="imageUploadForm" encType="multipart/form-data">
                <input type="file" id="imageFileInput" name="imageFile" accept="image/*" />
                <button type="button" onClick={uploadImage}>Upload</button>
            </form>
            <hr />
            <h2>Variation Image</h2>
            <div id="variationImageContainer">
                {/* Set the initial src attribute to a placeholder or blank */}
                <img id="variationImage" src="#" alt="Variation Image" />
            </div>
        </div>
    );
}

export default ImageUpload;
