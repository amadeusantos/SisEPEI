export function base64ToFile(base64String, fileName) {
    if (!base64String || !fileName) return null;

    const base64Data = base64String?.toString()?.split(',')[1];

    const typeMime = base64String?.toString()?.split(',')[0].replace("data:", "").replace(" ", "").replace(";base64", "");

    const byteCharacters = atob(base64Data);
    const byteNumbers = new Array(byteCharacters.length);

    for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }

    const byteArray = new Uint8Array(byteNumbers);

    return new File([byteArray], fileName, { type: typeMime });
}